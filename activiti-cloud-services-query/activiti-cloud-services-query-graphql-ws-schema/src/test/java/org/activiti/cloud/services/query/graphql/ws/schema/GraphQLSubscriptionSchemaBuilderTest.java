/*
 * Copyright 2018 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.cloud.services.query.graphql.ws.schema;

import static org.assertj.core.api.Assertions.assertThat;

import org.activiti.cloud.services.query.qraphql.ws.schema.GraphQLSubscriptionSchemaBuilder;
import org.junit.Test;

public class GraphQLSubscriptionSchemaBuilderTest {

    @Test
    public void testNotificationsSchemaBuilderParsesSchemaFromClasspathURL() {
        GraphQLSubscriptionSchemaBuilder schemaBuilder = new GraphQLSubscriptionSchemaBuilder("classpath:activiti.graphqls");

        assertThat(schemaBuilder.getGraphQLSchema()).isNotNull();
        assertThat(schemaBuilder.getGraphQLSchema().getSubscriptionType()).isNotNull();
        assertThat(schemaBuilder.getGraphQLSchema().getSubscriptionType().getName()).isEqualTo("Subscription");
        assertThat(schemaBuilder.getGraphQLSchema().getSubscriptionType().getFieldDefinition("ProcessEngineNotification")).isNotNull();
    }

    @Test
    public void testNotificationsSchemaBuilderParsesSchemaFromHttpUrl() {
        GraphQLSubscriptionSchemaBuilder schemaBuilder = new GraphQLSubscriptionSchemaBuilder("https://raw.githubusercontent.com/Activiti/activiti-cloud-query-service/develop/activiti-cloud-services-query/activiti-cloud-services-query-graphql-ws-schema/src/main/resources/activiti.graphqls");

        assertThat(schemaBuilder.getGraphQLSchema()).isNotNull();
        assertThat(schemaBuilder.getGraphQLSchema().getSubscriptionType()).isNotNull();
        assertThat(schemaBuilder.getGraphQLSchema().getSubscriptionType().getName()).isEqualTo("Subscription");
        assertThat(schemaBuilder.getGraphQLSchema().getSubscriptionType().getFieldDefinition("ProcessEngineNotification")).isNotNull();
    }

    @Test(expected=RuntimeException.class)
    public void testNotificationsSchemaBuilderParsesSchemaNotFoundRuntimeException() {
        new GraphQLSubscriptionSchemaBuilder("http://notfound.com//activiti.graphqls");
    }

}
