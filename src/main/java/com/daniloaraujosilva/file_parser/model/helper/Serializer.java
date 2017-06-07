package com.daniloaraujosilva.file_parser.model.helper;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.TimeZone;

/**
 *
 */
public class Serializer {

	/**
	 * 
	 */
	private enum StrategyEnum {
		JACKSON;
	}

	/**
	 * 
	 */
	public final StrategyEnum strategy;

	/**
	 * 
	 */
	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 
	 */
	private Serializer() {
		strategy = StrategyEnum.JACKSON;

		objectMapper.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	/**
	 * 
	 */
	public static class Singleton {
		public static final Serializer INSTANCE = new Serializer();
	}

	/**
	 * 
	 * @return
	 */
	public static Serializer getInstance() {
		return Singleton.INSTANCE;
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String serialize(Object input) throws JsonGenerationException, JsonMappingException, IOException {
		if (StrategyEnum.JACKSON.equals(getInstance().strategy)) {
			return objectMapper.writeValueAsString(input);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param input
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public Boolean serializeToFile(Object input, String filePath) throws IOException {
		File file = new File(filePath);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		try {
			out.writeObject(serialize(input));
		} catch (Exception exception) {
			throw exception;
		} finally {
			out.close();
		}

		return true;
	}

	/**
	 * 
	 * @param input
	 * @param returningClass
	 * @return
	 * @throws JsonParseException
	 * @throws com.fasterxml.jackson.databind.JsonMappingException
	 * @throws IOException
	 */
	public <R> R unserialize(String input, Class<R> returningClass) throws JsonParseException, com.fasterxml.jackson.databind.JsonMappingException, IOException {
		if (StrategyEnum.JACKSON.equals(getInstance().strategy)) {
			return objectMapper.readValue(input, returningClass);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param input
	 * @param returningType
	 * @return
	 * @throws JsonParseException
	 * @throws com.fasterxml.jackson.databind.JsonMappingException
	 * @throws IOException
	 */
	public <R> R unserialize(String input, TypeReference<R> returningType) throws JsonParseException, com.fasterxml.jackson.databind.JsonMappingException, IOException {
		if (StrategyEnum.JACKSON.equals(getInstance().strategy)) {
			return objectMapper.readValue(input, returningType);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param filePath
	 * @param returningClass
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public <R> R unserializeFromFile(String filePath, Class<R> returningClass) throws IOException, ClassNotFoundException {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fis);
		String json = null;
		try {
			json = (String) in.readObject();
		} catch (Exception exception) {
			throw exception;
		} finally {
			in.close();
		}

		return unserialize(json, returningClass);
	}

	/**
	 * 
	 * @param filePath
	 * @param returningType
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public <R> R unserializeFromFile(String filePath, TypeReference<R> returningType) throws IOException, ClassNotFoundException {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fis);
		String json = null;
		try {
			json = (String) in.readObject();
		} catch (Exception exception) {
			throw exception;
		} finally {
			in.close();
		}

		return unserialize(json, returningType);
	}
}
