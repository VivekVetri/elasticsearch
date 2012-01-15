/*
 * Licensed to Elastic Search and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.action.admin.indices.analyze;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.support.BaseIndicesRequestBuilder;
import org.elasticsearch.client.IndicesAdminClient;

/**
 *
 */
public class AnalyzeRequestBuilder extends BaseIndicesRequestBuilder<AnalyzeRequest, AnalyzeResponse> {

    public AnalyzeRequestBuilder(IndicesAdminClient indicesClient, String index, String text) {
        super(indicesClient, new AnalyzeRequest(index, text));
    }

    /**
     * Sets the index to use to analyzer the text (for example, if it holds specific analyzers
     * registered).
     */
    public AnalyzeRequestBuilder setIndex(String index) {
        request.index(index);
        return this;
    }

    /**
     * Sets the analyzer name to use in order to analyze the text.
     *
     * @param analyzer The analyzer name.
     */
    public AnalyzeRequestBuilder setAnalyzer(String analyzer) {
        request.analyzer(analyzer);
        return this;
    }

    /**
     * Sets the field that its analyzer will be used to analyze the text. Note, requires an index
     * to be set.
     */
    public AnalyzeRequestBuilder setField(String field) {
        request.field(field);
        return this;
    }

    /**
     * Instead of setting the analyzer, sets the tokenizer that will be used as part of a custom
     * analyzer.
     */
    public AnalyzeRequestBuilder setTokenizer(String tokenizer) {
        request.tokenizer(tokenizer);
        return this;
    }

    /**
     * Sets token filters that will be used on top of a tokenizer provided.
     */
    public AnalyzeRequestBuilder setTokenFilters(String... tokenFilters) {
        request.tokenFilters(tokenFilters);
        return this;
    }

    /**
     * if this operation hits a node with a local relevant shard, should it be preferred
     * to be executed on, or just do plain round robin. Defaults to <tt>true</tt>
     */
    public AnalyzeRequestBuilder setPreferLocal(boolean preferLocal) {
        request.preferLocal(preferLocal);
        return this;
    }

    @Override
    protected void doExecute(ActionListener<AnalyzeResponse> listener) {
        client.analyze(request, listener);
    }
}