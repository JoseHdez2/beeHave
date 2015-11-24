package main.model.sim;

import java.util.ArrayList;
import java.util.HashMap;

import agent.ar.ActionReactionAgent;
import srpg.Unit;

public class UnitSensor extends ActionReactionAgent implements Unit{

    public UnitSensor(HashMap<ArrayList<String>, String> lut) {
        super(lut);
        getLut().put("asd", "");
    }
    
    @Override
    public void sense() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void think() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void act() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean canPass() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canStay() {
        // TODO Auto-generated method stub
        return false;
    }

}
