package com.flexycore.iphone.launch;

import com.flexycore.iphone.uikit.UIApplicationDelegate;

import fr.emn.killerplop.iphone.test.IPhoneTest;

public class ApplicationContext {
	UIApplicationDelegate application;
	
	public ApplicationContext()
	{
		// Instanciate here the UIApplicationDelegate application to launch
		application = new IPhoneTest();
	}
	
	public UIApplicationDelegate getDelegate() {
		return application;
	}
}

