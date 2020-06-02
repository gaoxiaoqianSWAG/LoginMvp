package com.example.loginmvp.base;

import java.util.ArrayList;

public abstract class BasePresenter <V extends BaseView>{

    protected V mView;
    private ArrayList<BaseModel> models=new ArrayList<>();


    public void setmView(V mView) {
        this.mView = mView;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void onDestroy(){
        //打断P层和V层的联系
        mView=null;
        if(models.size()>0){
            for (BaseModel model : models) {
                model.onDestroy();
            }
            models.clear();
        }
    }
    public void addModel(BaseModel model){
        models.add(model);
    }
}
