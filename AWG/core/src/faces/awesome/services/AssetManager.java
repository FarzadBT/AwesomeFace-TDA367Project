package faces.awesome.services;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import faces.awesome.model.item.Item;
import java.util.HashMap;
import java.util.Map;

// Author: Philip Nilsson
// Updated by: Linus Wallman - Changed static type from HashMap -> Map also updated some types.
// update by: Philip Nilsson - Class is now a singleton

// This class is intended to manage game assets such as animations, textures and items.

public class AssetManager {

    //HashMaps containing data that is to be stored
    private Map<String, TextureRegion> textures;
    private Map<String, Item> items;
    private Map<String, Animation<TextureRegion> > animations;
    private Map<String, FileHandle> fileHandles;

    private AssetManager(){
        textures = new HashMap<>();
        items = new HashMap<>();
        animations = new HashMap<>();
    }

    private static AssetManager assets = new AssetManager();

    public static AssetManager getInstance(){
        return assets;
    }

    //Methods for adding new assets to maps as well as accessing already stored assets.
    public void addTexture(String name, TextureRegion texture){
        textures.put(name, texture);
    }

    public TextureRegion getTexture(String name){
        return textures.get(name);
    }

    public void addItem(String name, Item item){
        items.put(name, item);
    }

    public Item getItem(String name){
        return items.get(name);
    }

    public void addAnimation(String name, Animation<TextureRegion> anim){
        animations.put(name, anim);
    }

    public Animation<TextureRegion> getAnimation(String name){
        return animations.get(name);
    }

    public void addFileHandle(String name, FileHandle handle) {
        fileHandles.put(name, handle);
    }

    public FileHandle getFileHandle(String name) {
        return fileHandles.get(name);
    }

}
