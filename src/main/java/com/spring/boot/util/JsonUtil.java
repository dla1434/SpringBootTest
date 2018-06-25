/* Copyright (c) 2012 KT, Inc.
 * All right reserved.
 * http://www.ucloud.com
 * This software is the confidential and proprietary information of KT
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with KT.
 *
 * Revision History
 * Author              Date             Description
 * ------------------   --------------    ------------------
 *   taeki.kim           2012. 2. 1
 */
package com.spring.boot.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public final class JsonUtil {

	// ~ Static fields/initializers =====================================================================================
	// ~ Instance fields ================================================================================================
	// ~ Constructors ===================================================================================================
	// ~ Implementation Method (interface/abstract) =====================================================================
	// ~ Self Methods ===================================================================================================

	
	/**
	 * <p>
	 * 객체??메모�?구조�?json ?�료?�태�?�?��
	 * 
	 * @param object the object
	 * @return the string
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws Exception the exception
	 */
	public static String marshallingJson(Object object) throws JsonGenerationException, JsonMappingException, IOException {
//		TokenBuffer buffer = new TokenBuffer(null);
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
//		objectMapper.writeValue(buffer, object);
//		JsonNode root = objectMapper.readTree(buffer.asParser());
//		String jsonText = objectMapper.writeValueAsString(root);
//		jsonText = jsonText.replaceAll("null", "\"\"");
//		return jsonText;
		
		if (object != null){
			ObjectMapper mapper = new ObjectMapper();
			String jsonText = mapper.writeValueAsString(object);
			return jsonText.replaceAll("null", "\"\"");
		} else {
			return "";
		}
		
	}

	/**
	 * <p>
	 * description about class
	 * </p>
	 * Unmarshalling json.
	 * 
	 * @param <T> the
	 * @param jsonText the json text
	 * @param valueType the value type
	 * @return the t
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws Exception the exception
	 */
	public static <T> T unmarshallingJson(String jsonText, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String data = jsonText.replaceAll("null", "\"\"");
		return (T) objectMapper.readValue(data, valueType);
	}
	
	// ~ Getter and Setter ==============================================================================================
}
