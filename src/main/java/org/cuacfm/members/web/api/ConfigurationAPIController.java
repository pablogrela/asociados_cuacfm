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
package org.cuacfm.members.web.api;

import static org.cuacfm.members.model.util.FirebaseUtils.getEmailOfToken;

import java.util.List;

import org.cuacfm.members.model.element.ElementDTO;
import org.cuacfm.members.model.elementservice.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

/** The Class ConfigurationController. */
@Controller
public class ConfigurationAPIController {

	@Autowired
	private ElementService elementService;

	/**
	 * Gets the programs API.
	 *
	 * @param token the token
	 * @return the programs API
	 */
	@RequestMapping(value = "api/elementList/")
	public ResponseEntity<String> getElementsAPI(@RequestParam(value = "token") String token) {

		// Validate Token and retrieve email
		String email = getEmailOfToken(token);

		if (email != null) {
			List<ElementDTO> elementsDTO = elementService.getElementsDTO(elementService.getElementListReservable());
			String elementsJson = new Gson().toJson(elementsDTO);
			return new ResponseEntity<>(elementsJson, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
}
