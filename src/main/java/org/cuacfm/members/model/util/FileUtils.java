/**
 * Copyright (C) 2015 Pablo Grela Palleiro (pablogp_9@hotmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cuacfm.members.model.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * The Class FileUtils.
 */
public class FileUtils {

	private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

	/**
	 * Instantiates a new file utils.
	 */
	private FileUtils() {
		super();
	}

	/**
	 * Creates the folder if no exist.
	 *
	 * @param directoryName the directory name
	 */
	public static void createFolderIfNoExist(String directoryName) {
		File theDir = new File(directoryName);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			LOGGER.info("Creating directory: " + directoryName);
			boolean result = false;

			try {
				theDir.mkdirs();
				result = true;
			} catch (SecurityException se) {
				LOGGER.severe("Error creating directory: " + se.getStackTrace());
			}
			if (result) {
				LOGGER.info("Created directory: " + directoryName);
			}
		}
	}
	
	/**
	 * Download File.
	 *
	 * @param path the path
	 * @param file the file
	 * @param mediaType the media type
	 * @return the response entity
	 */
	public static ResponseEntity<byte[]> downloadFile(String path, String file, MediaType mediaType) {
		Path pathAux = Paths.get(path + file);
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(pathAux);
		} catch (IOException e) {
			e.getMessage();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{file}").buildAndExpand(file).toUri());
		headers.add("content-disposition", "attachment; filename=" + file + ";");
		return new ResponseEntity<>(contents, headers, HttpStatus.OK);
	}
}
