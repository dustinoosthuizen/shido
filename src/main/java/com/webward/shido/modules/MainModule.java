package com.webward.shido.modules;

import com.webward.shido.jaxrs.ClientErrorExceptionMapper;
//import com.webward.shido.resources.TestResource2;
import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;

//import com.webward.jaxrs.GsonMessageBodyHandler;

/**
 * Created by dustinosthzn on 2014/11/30.
 */
public class MainModule extends RequestScopeModule {


    @Override
    protected void configure() {

        super.configure();
//        bind(GsonMessageBodyHandler.class);
//        bind(TestResource2.class);
        bind(ClientErrorExceptionMapper.class);
    }
}
