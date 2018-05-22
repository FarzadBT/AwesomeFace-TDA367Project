package faces.awesome.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import faces.awesome.model.item.Item;

import java.util.HashMap;

// Author: Philip Nilsson

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
    //Methods for adding new assets to maps as well as accessing already stored assets.
    public void addTexture(String name, Texture texture){
        Textures.put(name, texture);
    }

    public Texture getTexture(String name){
        return Textures.get(name);
    }

    public void addItem(String name, Item item){
        Items.put(name, item);
    }

    public Item getItem(String name){
        return Items.get(name);
    }

    public void addAnimation(String name, TextureRegion anim){
        Animations.put(name, anim);
    }

    public TextureRegion getAnimation(String name){
        return Animations.get(name);
    }

}
