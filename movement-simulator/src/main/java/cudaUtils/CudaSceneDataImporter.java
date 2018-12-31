package cudaUtils;

import utility.Position;

import java.util.List;

public class CudaSceneDataImporter {

    static public final String METADATA_FILE_PATH = "path";
    static public final String PATHS_FILE_PATH = "path2";

    static public CudaSceneMetadata getCudaSceneMetadata(){
        return null;
    }

    static public List<List<Position>> getAgentsPaths(){
        return null;
    }

    static public CudaSceneDataBox getCudaSceneData(){
        CudaSceneDataBox sceneDataBox = new CudaSceneDataBox();
        sceneDataBox.setPaths(getAgentsPaths());
        sceneDataBox.setCudaSceneMetadata(getCudaSceneMetadata());
        return sceneDataBox;
    }
}