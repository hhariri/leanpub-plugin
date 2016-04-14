package com.hadihariri.leanpub

import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair

/**
 * Created by hadihariri on 23/01/16.
 */

class LeanpubAPI {

    fun requestPreview(apikey: String, slug: String): LeanpubAPIResponse {
        if (apikey.isEmpty() || slug.isEmpty()) {
            return LeanpubAPIResponse("Please define your API key and URL slug under Tools | Leanpub before requesting a preview", true)
        }
        return makeRequest(apikey, "https://leanpub.com/$slug/preview.json")
    }

    fun requestPublish(apikey: String, slug: String): LeanpubAPIResponse {
        if (apikey.isEmpty() || slug.isEmpty()) {
            return LeanpubAPIResponse("Please define your API key and URL slug under Tools | Leanpub before publishing", true)
        }
        return makeRequest(apikey, "https://leanpub.com/$slug/publish.json")
    }

    private fun makeRequest(apikey: String, url: String): LeanpubAPIResponse {
        val httpClient = HttpClients.createDefault()!!
        val httpPost = HttpPost(url)
        val previewData = arrayListOf<NameValuePair>()
        previewData.add(BasicNameValuePair("api_key", apikey))
        httpPost.entity = UrlEncodedFormEntity(previewData)
        val response = httpClient.execute(httpPost)
        return LeanpubAPIResponse(if (response.statusLine.reasonPhrase == "OK") "Request successful" else response.statusLine.reasonPhrase, response.statusLine.statusCode != 200)
    }



}