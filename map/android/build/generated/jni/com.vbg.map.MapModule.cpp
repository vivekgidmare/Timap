/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2011-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

/** This code is generated, do not edit by hand. **/

#include "com.vbg.map.MapModule.h"

#include "AndroidUtil.h"
#include "EventEmitter.h"
#include "JNIUtil.h"
#include "JSException.h"
#include "Proxy.h"
#include "ProxyFactory.h"
#include "TypeConverter.h"
#include "V8Util.h"



#include "com.vbg.map.AnnotationProxy.h"
#include "com.vbg.map.RouteProxy.h"
#include "com.vbg.map.ViewProxy.h"

#include "org.appcelerator.kroll.KrollModule.h"

#define TAG "MapModule"

using namespace v8;

		namespace com {
		namespace vbg {
		namespace map {


Persistent<FunctionTemplate> MapModule::proxyTemplate = Persistent<FunctionTemplate>();
jclass MapModule::javaClass = NULL;

MapModule::MapModule(jobject javaObject) : titanium::Proxy(javaObject)
{
}

void MapModule::bindProxy(Handle<Object> exports)
{
	if (proxyTemplate.IsEmpty()) {
		getProxyTemplate();
	}

	// use symbol over string for efficiency
	Handle<String> nameSymbol = String::NewSymbol("Map");

	Local<Function> proxyConstructor = proxyTemplate->GetFunction();
	Local<Object> moduleInstance = proxyConstructor->NewInstance();
	exports->Set(nameSymbol, moduleInstance);
}

void MapModule::dispose()
{
	LOGD(TAG, "dispose()");
	if (!proxyTemplate.IsEmpty()) {
		proxyTemplate.Dispose();
		proxyTemplate = Persistent<FunctionTemplate>();
	}

	titanium::KrollModule::dispose();
}

Handle<FunctionTemplate> MapModule::getProxyTemplate()
{
	if (!proxyTemplate.IsEmpty()) {
		return proxyTemplate;
	}

	LOGD(TAG, "GetProxyTemplate");

	javaClass = titanium::JNIUtil::findClass("com/vbg/map/MapModule");
	HandleScope scope;

	// use symbol over string for efficiency
	Handle<String> nameSymbol = String::NewSymbol("Map");

	Handle<FunctionTemplate> t = titanium::Proxy::inheritProxyTemplate(
		titanium::KrollModule::getProxyTemplate()
, javaClass, nameSymbol);

	proxyTemplate = Persistent<FunctionTemplate>::New(t);
	proxyTemplate->Set(titanium::Proxy::inheritSymbol,
		FunctionTemplate::New(titanium::Proxy::inherit<MapModule>)->GetFunction());

	titanium::ProxyFactory::registerProxyPair(javaClass, *proxyTemplate);

	// Method bindings --------------------------------------------------------
	DEFINE_PROTOTYPE_METHOD(proxyTemplate, "isGooglePlayServicesAvailable", MapModule::isGooglePlayServicesAvailable);

	Local<ObjectTemplate> prototypeTemplate = proxyTemplate->PrototypeTemplate();
	Local<ObjectTemplate> instanceTemplate = proxyTemplate->InstanceTemplate();

	// Delegate indexed property get and set to the Java proxy.
	instanceTemplate->SetIndexedPropertyHandler(titanium::Proxy::getIndexedProperty,
		titanium::Proxy::setIndexedProperty);

	// Constants --------------------------------------------------------------
	JNIEnv *env = titanium::JNIScope::getEnv();
	if (!env) {
		LOGE(TAG, "Failed to get environment in MapModule");
		//return;
	}


		DEFINE_INT_CONSTANT(prototypeTemplate, "SUCCESS", 0);

		DEFINE_INT_CONSTANT(prototypeTemplate, "ANNOTATION_DRAG_STATE_START", 0);

		DEFINE_INT_CONSTANT(prototypeTemplate, "SATELLITE_TYPE", 2);

		DEFINE_INT_CONSTANT(prototypeTemplate, "NORMAL_TYPE", 1);

		DEFINE_INT_CONSTANT(prototypeTemplate, "SERVICE_VERSION_UPDATE_REQUIRED", 2);

		DEFINE_INT_CONSTANT(prototypeTemplate, "HYBRID_TYPE", 4);

		DEFINE_NUMBER_CONSTANT(prototypeTemplate, "ANNOTATION_VIOLET", 270);

		DEFINE_INT_CONSTANT(prototypeTemplate, "SERVICE_DISABLED", 3);

		DEFINE_NUMBER_CONSTANT(prototypeTemplate, "ANNOTATION_CYAN", 180);

		DEFINE_NUMBER_CONSTANT(prototypeTemplate, "ANNOTATION_ORANGE", 30);

		DEFINE_NUMBER_CONSTANT(prototypeTemplate, "ANNOTATION_RED", 0);

		DEFINE_NUMBER_CONSTANT(prototypeTemplate, "ANNOTATION_BLUE", 240);

		DEFINE_INT_CONSTANT(prototypeTemplate, "SERVICE_MISSING", 1);

		DEFINE_NUMBER_CONSTANT(prototypeTemplate, "ANNOTATION_YELLOW", 60);

		DEFINE_NUMBER_CONSTANT(prototypeTemplate, "ANNOTATION_MAGENTA", 300);

		DEFINE_INT_CONSTANT(prototypeTemplate, "ANNOTATION_DRAG_STATE_END", 1);

		DEFINE_INT_CONSTANT(prototypeTemplate, "SERVICE_INVALID", 9);

		DEFINE_NUMBER_CONSTANT(prototypeTemplate, "ANNOTATION_GREEN", 120);

		DEFINE_NUMBER_CONSTANT(prototypeTemplate, "ANNOTATION_ROSE", 330);

		DEFINE_NUMBER_CONSTANT(prototypeTemplate, "ANNOTATION_AZURE", 210);

		DEFINE_INT_CONSTANT(prototypeTemplate, "TERRAIN_TYPE", 3);


	// Dynamic properties -----------------------------------------------------

	// Accessors --------------------------------------------------------------

	return proxyTemplate;
}

// Methods --------------------------------------------------------------------
Handle<Value> MapModule::isGooglePlayServicesAvailable(const Arguments& args)
{
	LOGD(TAG, "isGooglePlayServicesAvailable()");
	HandleScope scope;

	JNIEnv *env = titanium::JNIScope::getEnv();
	if (!env) {
		return titanium::JSException::GetJNIEnvironmentError();
	}
	static jmethodID methodID = NULL;
	if (!methodID) {
		methodID = env->GetMethodID(MapModule::javaClass, "isGooglePlayServicesAvailable", "()I");
		if (!methodID) {
			const char *error = "Couldn't find proxy method 'isGooglePlayServicesAvailable' with signature '()I'";
			LOGE(TAG, error);
				return titanium::JSException::Error(error);
		}
	}

	titanium::Proxy* proxy = titanium::Proxy::unwrap(args.Holder());

	jvalue* jArguments = 0;

	jobject javaProxy = proxy->getJavaObject();
	jint jResult = (jint)env->CallIntMethodA(javaProxy, methodID, jArguments);



	if (!JavaObject::useGlobalRefs) {
		env->DeleteLocalRef(javaProxy);
	}



	if (env->ExceptionCheck()) {
		Handle<Value> jsException = titanium::JSException::fromJavaException();
		env->ExceptionClear();
		return jsException;
	}


	Handle<Number> v8Result = titanium::TypeConverter::javaIntToJsNumber(env, jResult);



	return v8Result;

}

// Dynamic property accessors -------------------------------------------------


		} // map
		} // vbg
		} // com
