/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 * Warning: This file is GENERATED, and should not be modified
 */
var bootstrap = kroll.NativeModule.require("bootstrap"),
	invoker = kroll.NativeModule.require("invoker"),
	Titanium = kroll.binding("Titanium").Titanium;

function moduleBootstrap(moduleBinding) {
	function lazyGet(object, binding, name, namespace) {
		return bootstrap.lazyGet(object, binding,
			name, namespace, moduleBinding.getBinding);
	}

	var module = moduleBinding.getBinding("com.vbg.map.MapModule")["Map"];
	var invocationAPIs = module.invocationAPIs = [];
	module.apiName = "Map";

	function addInvocationAPI(module, moduleNamespace, namespace, api) {
		invocationAPIs.push({ namespace: namespace, api: api });
	}

		addInvocationAPI(module, "Map", "Map", "createAnnotation");
	addInvocationAPI(module, "Map", "Map", "createRoute");
	addInvocationAPI(module, "Map", "Map", "createView");

			if (!("__propertiesDefined__" in module)) {		
		Object.defineProperties(module, {
			"Route": {
				get: function() {
					var Route = lazyGet(this, "com.vbg.map.RouteProxy", "Route", "Route");
					return Route;
				},
				configurable: true
			},
			"Annotation": {
				get: function() {
					var Annotation = lazyGet(this, "com.vbg.map.AnnotationProxy", "Annotation", "Annotation");
					return Annotation;
				},
				configurable: true
			},
			"View": {
				get: function() {
					var View = lazyGet(this, "com.vbg.map.ViewProxy", "View", "View");
					return View;
				},
				configurable: true
			},
		
		});
		module.constructor.prototype.createAnnotation = function() {
			return new module.Annotation(arguments);
		}
		module.constructor.prototype.createRoute = function() {
			return new module.Route(arguments);
		}
		module.constructor.prototype.createView = function() {
			return new module.View(arguments);
		}
		}
		module.__propertiesDefined__ = true;
		return module;

}
exports.bootstrap = moduleBootstrap;
