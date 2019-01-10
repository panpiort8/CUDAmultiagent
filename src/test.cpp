#include "agent.h"
#include "engine.h"
#include "smath.h"
#include <stdio.h>
#include <iostream>
#include <string.h>
#include <assert.h>

using namespace std;

float rand_float(float min, float max) {
    if (max == min) return max;
    if (max < min) return ((((float) rand()) / (float) RAND_MAX) * (min - max)) + max;
    return ((((float) rand()) / (float) RAND_MAX) * (max - min)) + min;
}

static void testRayIntersect();

static void test(){
    testRayIntersect();
}

int main(int argc, char const *argv[])
{
    if(argc > 1 and strcmp(argv[1], "--test") == 0){
        cout << "testing...\n"; 
        test();
        return 0;
    }

    int n_agents, n_generations, board_x, board_y;
    float max_speed;
    float agent_radius;
    cin >> n_agents >> n_generations >> agent_radius >> board_x >> board_y >> max_speed;
    
    agent *agents = new agent[n_agents];
    srand(time(NULL));
    for (int i = 0; i < n_agents; ++i) {
        agents[i].set_agent(rand_float(0, board_x), rand_float(0, board_y), rand_float(0, board_x),
                            rand_float(0, board_y));
        agents[i].vect() = agents[i].vect().normalized();
    }

    run(n_agents, n_generations, agent_radius, max_speed, board_x, board_y, agents);
    return 0;
}

static void testRayIntersect(){
    {
        ray ray1(0, 0, 0, 1);
        ray ray2(2, 3, -1, 0);
        vec2 p = intersect_rays(ray1, ray2);
        assert(p.x() == 0 and p.y() == 3);
        cout << p.x() << ' ' << p.y() << '\n';
    }
    {
        ray ray1(0, 0, 0, 1);
        ray ray2(0, 0, 1, 0);
        vec2 p = intersect_rays(ray1, ray2);
        assert(p.x() == 0 and p.y() == 0);
        cout << p.x() << ' ' << p.y() << '\n';
    }
    {
        ray ray1(0, 1, 0, 1);
        ray ray2(1, 1, 0, 1);
        vec2 p = intersect_rays(ray1, ray2);
        assert(isnan(p.x()) and isnan(p.y()));
        cout << p.x() << ' ' << p.y() << '\n';
    }
    cout << "rayTest: OK\n";
}