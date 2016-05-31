/*
 * #%L
 * GarethHealy :: Elastoplast :: Rule1
 * %%
 * Copyright (C) 2013 - 2016 Gareth Healy
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.garethahealy.elastoplast.rule1.buildconfig;

import io.fabric8.kubernetes.generator.annotation.KubernetesModelProcessor;
import io.fabric8.openshift.api.model.DeploymentConfigBuilder;
import io.fabric8.openshift.api.model.ImageStreamBuilder;
import io.fabric8.openshift.api.model.TemplateBuilder;

@KubernetesModelProcessor
public class MainKubernetesModelProcessor {

    public void withDeploymentConfigBuilderTemplate(DeploymentConfigBuilder builder) {
        new DeploymentConfigKubernetesModelProcessor().on(builder);
    }

    public void withImageStreamBuilderTemplate(ImageStreamBuilder builder) {
        new ImageStreamKubernetesModelProcessor().on(builder);
    }

    public void withTemplateBuilder(TemplateBuilder builder) {
        new BuildConfigKubernetesModelProcessor().on(builder);
    }
}
