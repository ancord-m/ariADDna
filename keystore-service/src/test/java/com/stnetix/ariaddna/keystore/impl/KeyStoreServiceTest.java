package com.stnetix.ariaddna.keystore.impl;

import com.stnetix.ariaddna.keystore.IKeyStore;
import com.stnetix.ariaddna.keystore.config.KeyStoreConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 19.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = KeyStoreConfig.class)
public class KeyStoreServiceTest {
    @Autowired
    private IKeyStore keyStore;

    @Test
    public void getCertByUUID() throws Exception {
        UUID uuid = UUID.randomUUID();
        //IKeyStore keyStore = new KeyStoreService();
        File keyStoreFile = keyStore.generateKeyStore();
        File cert = keyStore.generateCert(uuid);
        keyStore.storeCert(cert,keyStoreFile);
        File searchedCert = keyStore.getCertByUUID(uuid,keyStoreFile);
        assertTrue(searchedCert.exists());
    }

    @Test
    public void removeCert() throws Exception {
        UUID uuid = UUID.randomUUID();
        IKeyStore keyStore = new KeyStoreService();
        File keyStoreFile = keyStore.generateKeyStore();
        File cert = keyStore.generateCert(uuid);
        keyStore.storeCert(cert,keyStoreFile);
        keyStore.removeCert(cert,keyStoreFile);
        assertFalse(keyStore.isKeyStoreContainCert(cert,keyStoreFile));
    }

    @Test
    public void disableCert() throws Exception {
        UUID uuid = UUID.randomUUID();
        IKeyStore keyStore = new KeyStoreService();
        File keyStoreFile = keyStore.generateKeyStore();
        //File disableKeyStoreFile = keyStore.generateDisableKeyStore();
        File cert = keyStore.generateCert(uuid);
        keyStore.storeCert(cert,keyStoreFile);
        //keyStore.disableCert(cert,keyStoreFile,disableKeyStoreFile);
        //assertTrue(keyStore.isKeyStoreContainCert(cert,disableKeyStoreFile) && !keyStore.isKeyStoreContainCert(cert, keyStoreFile));
    }

    @Test
    public void isKeyStoreContainCert() throws Exception {
        UUID uuid = UUID.randomUUID();
        IKeyStore keyStore = new KeyStoreService();
        File keyStoreFile = keyStore.generateKeyStore();
        File cert = keyStore.generateCert(uuid);
        keyStore.storeCert(cert,keyStoreFile);
        assertTrue(keyStore.isKeyStoreContainCert(cert, keyStoreFile));
    }

    @Test
    public void storeCert() throws Exception {
        IKeyStore keyStore = new KeyStoreService();
        File keyStoreFile = keyStore.generateKeyStore();
        File cert = keyStore.generateCert(UUID.randomUUID());
        keyStore.storeCert(cert,keyStoreFile);
    }

    @Test
    public void isValidCert() throws Exception {
        IKeyStore keyStore = new KeyStoreService();
        File cert = keyStore.generateCert(UUID.randomUUID());
        assertTrue(keyStore.isValidCert(cert));
    }

    @Test
    public void generateCert() throws Exception {
        IKeyStore keyStore = new KeyStoreService();
        File cert = keyStore.generateCert(UUID.randomUUID());
        System.out.println(cert.getAbsolutePath());
        assertNotNull(cert);

    }

    @Test
    public void generateKeyStore() throws Exception {
        IKeyStore keyStore = new KeyStoreService();
        File keyStoreFile = keyStore.generateKeyStore();
        System.out.println(keyStoreFile.getAbsolutePath());
        assertNotNull(keyStoreFile);
    }

}