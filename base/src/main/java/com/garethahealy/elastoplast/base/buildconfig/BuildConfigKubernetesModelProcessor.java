/*
 * #%L
 * GarethHealy :: Elastoplast :: Base
 * %%
 * Copyright (C) 2013 - 2017 Gareth Healy
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
package com.garethahealy.elastoplast.base.buildconfig;

import java.util.HashMap;
import java.util.Map;

import io.fabric8.kubernetes.api.model.ObjectReference;
import io.fabric8.openshift.api.model.BuildTriggerPolicy;
import io.fabric8.openshift.api.model.ImageChangeTrigger;
import io.fabric8.openshift.api.model.TemplateBuilder;

public class BuildConfigKubernetesModelProcessor {

    public void on(TemplateBuilder builder) {
        builder.addNewBuildConfigObject()
                .withNewMetadata()
                    .withName("elastoplast-base")
                    .withLabels(getLabels())
                .endMetadata()
                .withNewSpec()
                    .withTriggers(getTriggers())
                    .withNewSource()
                        .withNewGit()
                            .withUri("https://github.com/garethahealy/elastoplast.git")
                        .endGit()
                        .withContextDir("base/src/main/docker")
                        .withType("Git")
                    .endSource()
                    .withNewStrategy()
                        .withNewDockerStrategy()
                            .withNewFrom()
                                .withKind("ImageStreamTag")
                                .withName("python-27-rhel7:latest")
                                .withNamespace("openshift")
                            .endFrom()
                        .endDockerStrategy()
                        .withType("Source")
                    .endStrategy()
                    .withNewOutput()
                        .withNewTo()
                            .withKind("ImageStreamTag")
                            .withName("elastoplast-base:${IS_TAG}")
                        .endTo()
                    .endOutput()
                .endSpec()
            .endBuildConfigObject()
            .build();
    }

    private BuildTriggerPolicy getTriggers() {
        ObjectReference from = new ObjectReference();
        from.setName("python-27-rhel7:latest");
        from.setKind("ImageStreamTag");
        from.setNamespace("openshift");

        ImageChangeTrigger imageChangeTrigger = new ImageChangeTrigger();
        imageChangeTrigger.setFrom(from);

        BuildTriggerPolicy policy = new BuildTriggerPolicy();
        policy.setType("ImageChange");
        policy.setImageChange(imageChangeTrigger);

        return policy;
    }

    private Map<String, String> getLabels() {
        Map<String, String> labels = new HashMap<>();
        labels.put("app", "elastoplast-base");
        labels.put("project", "elastoplast-base");
        labels.put("version", "1.0.0-SNAPSHOT");
        labels.put("group", "garethahealy");

        return labels;
    }
}
