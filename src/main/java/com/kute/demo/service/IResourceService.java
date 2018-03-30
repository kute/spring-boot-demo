package com.kute.demo.service;

/**
 * Created by kute on 2017/12/9.
 */
public interface IResourceService {


    void openRoom(String caller);

    void updatePrice(String caller);

    void queryCommon(String caller);

    void normalThing(String caller);

}
