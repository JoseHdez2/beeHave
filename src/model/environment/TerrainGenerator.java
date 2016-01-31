package model.environment;

import util.math.RandomNum;
import util.typedef.Matrix;

/**
 * Collection of functions for terrain generation. Called by EnvironmentModel.
 * 
 * @author jose
 */
public abstract class TerrainGenerator {
    
    /**
     * @param width Number of columns of generated terrain.
     * @param height Number of rows of generated terrain.
     * @param type The only type of terrain the environment will have.
     * @return Plain terrain with only one type of terrain.
     */
    static public Matrix<EnvironmentModel.Terrain> plain(int width, int height, EnvironmentModel.Terrain type){
        Matrix<EnvironmentModel.Terrain> terrain = 
                new Matrix<EnvironmentModel.Terrain>(new EnvironmentModel.Terrain[width][height]);
        
        for (int j = 0; j < terrain.height(); j++){
            for (int i = 0; i < terrain.width(); i++){
                terrain.set(i, j, type);
            }
        }
        
        return terrain;
    }
    
    /**
     * @param width Number of columns of generated terrain.
     * @param height Number of rows of generated terrain.
     * @param types The types of terrain the environment will have.
     * @return Terrain with each tile being of a random type.
     */
    static public Matrix<EnvironmentModel.Terrain> random(int width, int height, EnvironmentModel.Terrain[] types){
        Matrix<EnvironmentModel.Terrain> terrain = 
                new Matrix<EnvironmentModel.Terrain>(new EnvironmentModel.Terrain[width][height]);
        
        for (int j = 0; j < terrain.height(); j++){
            for (int i = 0; i < terrain.width(); i++){
                int ind = RandomNum.randInt(0, types.length-1);
                terrain.set(i, j, types[ind]);
            }
        }
        
        return terrain;
    }
    
    /**
     * Wrapper for {@link TerrainGenerator#random(int, int, model.environment.EnvironmentModel.Terrain[])}
     * that uses all existing terrain types.
     * @param width Number of columns of generated terrain.
     * @param height Number of rows of generated terrain.
     * @return Terrain with each tile being of a random type.
     */
    static public Matrix<EnvironmentModel.Terrain> random(int width, int height){
        return random(width, height, EnvironmentModel.Terrain.values());
    }
    
}
