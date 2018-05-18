package faces.awesome.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import faces.awesome.model.item.Item;

import java.util.HashMap;

// Author: Amloudhi

//This class is intended to manage game assets such as animations, textures and items.

public class AssetManager {
    //HashMaps containing data that is to be stored
    private HashMap<String, Texture> Textures;
    private HashMap<String, Item> Items;
    private HashMap<String, TextureRegion> Animations;

    public AssetManager(){
        Textures = new HashMap<>();
        Items = new HashMap<>();
        Animations = new HashMap<>();
    }

    public void addTexture(String name, Texture texture){
        Textures.put(name, texture);
    }

    public void getTexture(String name){
        Textures.get(name);
    }

    public void addItem(String name, Item item){
        Items.put(name, item);
    }

    public void getItem(String name){
        Items.get(name);
    }

    public void addAnimation(String name, TextureRegion anim){
        Animations.put(name, anim);
    }

    public void getAnimation(String name){
        Animations.get(name);
    }

}
