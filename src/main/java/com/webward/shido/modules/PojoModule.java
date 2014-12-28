package com.webward.shido.modules;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.webward.shido.config.Shido;
import com.webward.shido.transformer.ItemTransformer;
import com.webward.shido.transformer.UserTransformer;
import com.webward.shido.utils.CalendarUtil;
import com.webward.shido.utils.EncryptionUtil;
import com.webward.shido.utils.MailSenderUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.jasypt.digest.StandardStringDigester;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by dustinosthzn on 2014/11/30.
 */
public class PojoModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(ItemTransformer.class);
        binder.bind(UserTransformer.class);
        binder.bind(EncryptionUtil.class);
        binder.bind(MailSenderUtil.class);
        binder.bind(CalendarUtil.class);
        binder.bind(StandardPBEStringEncryptor.class);
        binder.bind(StandardStringDigester.class);
        binder.bind(ObjectMapper.class);

        try {
            Yaml yaml = new Yaml();
            InputStream input = this.getClass().getResourceAsStream("/shido.yml");
            Shido shido = yaml.loadAs(input,Shido.class);
            binder.bind(Shido.class).toInstance(shido);
        }catch(Exception exc)
        {
            exc.printStackTrace();
        }


    }
}
