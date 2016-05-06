/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.phoenixnap.oss.ramlapisync.generation.rules.basic;

import com.phoenixnap.oss.ramlapisync.data.ApiControllerMetadata;
import com.phoenixnap.oss.ramlapisync.generation.rules.Rule;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;

/**
 * Generates an class declaration based on the controller name in ApiControllerMetadata.
 *
 * INPUT:
 * #%RAML 0.8
 * title: myapi
 * mediaType: application/json
 * baseUri: /
 * /base:
 *
 * OUTPUT:
 * public class Base{suffix} {
 *
 * }
 *
 * @author kurtpa
 * @since 0.5.0
 */
public class ResourceClassDeclarationRule implements Rule<JPackage,JDefinedClass, ApiControllerMetadata> {

    private final String classNameSuffix;

    public ResourceClassDeclarationRule() {
        this("");
    }

    public ResourceClassDeclarationRule(String classNameSuffix) {
        this.classNameSuffix = classNameSuffix;
    }

    @Override
    public JDefinedClass apply(ApiControllerMetadata controllerMetadata, JPackage generatableType) {
        String resourceClassName = controllerMetadata.getResourceName() + classNameSuffix;
        JDefinedClass definedClass;
        try {
            definedClass = generatableType._class(resourceClassName);
        } catch (JClassAlreadyExistsException e1) {
            definedClass = generatableType._getClass(resourceClassName);
        }
        return definedClass;
    }

}