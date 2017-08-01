/*
 * #%L
 * GarethHealy :: Elastoplast :: Elastic Loader
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
package com.garethahealy.elastoplast.elasticloader;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

public class ElasticSearchClientFactory {

    private String homePath;

    public ElasticSearchClientFactory(String homePath) {
        this.homePath = homePath;
    }

    public Client getObject() throws Exception {
        return getNode().client();
    }

    private Node getNode() {
        Node node = NodeBuilder.nodeBuilder()
            .local(true)
            .settings(Settings.settingsBuilder()
                          .put("http.enabled", true)
                          .put("path.data", homePath + "/data")
                          .put("path.home", homePath)
                          .put("network.host", "_en0:ipv4_"))
            .node();

        return node;
    }
}
