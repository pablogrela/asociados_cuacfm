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
package org.cuacfm.members.web.support;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * The Class VerifyRecaptcha.
 */
public class VerifyRecaptcha {

	/** The Constant url. */
	public static final String URL = "https://www.google.com/recaptcha/api/siteverify";

	/** The Constant secret. */
	//	La clave secreta se debe cambiar el funcion del entorno
	public static final String SECRET = "6LeTZg8UAAAAAD2nWjiLQaEDB-zvdXxZtpbGnvO8";

	/**
	 * Instantiates a new verify recaptcha.
	 */
	protected VerifyRecaptcha() {
		super();
	}

	/**
	 * Verify.
	 *
	 * @param g-recaptcha-response the g recaptcha response
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static boolean verify(String gRecaptchaResponse) throws IOException {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}

		try {
			URL obj = new URL(URL);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			// add request header
			con.setRequestMethod("POST");
			String postParams = "secret=" + SECRET + "&response=" + gRecaptchaResponse;

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			if (response.toString().contains("true")) {
				return true;
			}
			return false;

		} catch (Exception e) {
			//Si entra por aqui es que no hay internet, por tanto se permite el registro
			return true;
		}
	}

}
