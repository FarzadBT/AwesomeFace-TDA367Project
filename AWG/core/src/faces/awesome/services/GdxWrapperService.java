package faces.awesome.services;

import faces.awesome.GDXWrapper;
import faces.awesome.model.AssetManager;

//@Author: Philip Nilsson

//This class is intended to serve as a wrapper for parts of the model that have to do with LibGDX.
//Contains a GDXWrapper and provides access to the fields within.

public class GdxWrapperService {
    private GDXWrapper gdxmodel;

    public GdxWrapperService(GDXWrapper gdxmodel){
        this.gdxmodel = gdxmodel;
    }

    //Returns an AssetManager. Used to access wrapped textures and animations as well as items.
    public AssetManager getAssets(){
        return gdxmodel.getAssets();
    }

    //Returns a WorldMap. Used to access wrapped WorldMap.
    public WorldMap getMap(){
        return gdxmodel.getMap();
    }


}
