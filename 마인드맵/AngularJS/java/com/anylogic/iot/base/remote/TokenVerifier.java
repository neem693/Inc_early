package com.anylogic.iot.base.remote;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
//import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * <PRE>
 *  ClassName : TokenVerifier
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 6. 9. 오후 8:35:25
 * @author  : jun
 * @brief   :
 */
@Component
public class TokenVerifier {
	private final Base64 decoder = new Base64();
	private final ObjectMapper mapper = new ObjectMapper();

//	  private final byte[] secret;
//    private final String audience;
//    private final String issuer;
//	  private Map<String, String> algorithms;

	private static final Logger logger = LoggerFactory.getLogger(TokenVerifier.class);

//	public TokenVerifier(String secret, String audience, String issuer) {
//        this(secret.getBytes(Charset.forName("UTF-8")), audience, issuer);
//    }
//
//    public TokenVerifier(String secret, String audience) {
//        this(secret, audience, null);
//    }
//
//    public TokenVerifier(String secret) {
//        this(secret, null, null);
//    }
//
//    public TokenVerifier(byte[] secret, String audience, String issuer) {
//        if (secret == null || secret.length == 0) {
//            throw new IllegalArgumentException("Secret cannot be null or empty");
//        }
//
//    	mapper = new ObjectMapper();
//
//        algorithms = new HashMap<String, String>();
//        algorithms.put("HS256", "HmacSHA256");
//        algorithms.put("HS384", "HmacSHA384");
//        algorithms.put("HS512", "HmacSHA512");
//
//        this.secret = secret;
//        this.audience = audience;
//        this.issuer = issuer;
//    }

//    public TokenVerifier(byte[] secret, String audience) {
//        this(secret, audience, null);
//    }
//
//    public TokenVerifier(byte[] secret) {
//        this(secret, null, null);
//    }


	/**
	 * <PRE>
	 *  MethodName : getTokenVerify
	 *               토큰 검증 처리
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 9. 오후 8:37:35
	 * @param  :
	 * @return : boolean
	 * @brief  :
	 * @param token
	 * @return
	 */
	public boolean getTokenVerify(String token){
		if (token == null || "".equals(token)) {
			return false;
        }

		String[] tokenSplit = token.split("\\.");
		if(tokenSplit.length != 3 ){
			return false;
		}

		// Token 검증 angular-jwt.js URL Safe Base64Decoding
		// Not Use
//		JsonNode jwtHeader = decodeAndParse(tokenSplit[0]);
//		if(jwtHeader == null) {
//			return false;
//		}
//		String algorithm = getAlgorithm(jwtHeader);

		JsonNode jwtPayload = decodeAndParse(tokenSplit[1]);
		if(jwtPayload == null) {
			return false;
		}
		// check signature Not Use
//      verifySignature(pieces, algorithm);

		// additional JWTClaims checks
//      verifyExpiration(jwtPayload);
//      verifyIssuer(jwtPayload);
//      verifyAudience(jwtPayload);
		Map map = null;
		try {
			map =  mapper.treeToValue(jwtPayload, Map.class);

			//expair date check
			if(map == null) {
				return false;
			}else {
				if ( map.get("exp") == null || "".equals(map.get("exp"))){
					return false;
				}else {

					Long tokenTime = Long.parseLong(String.valueOf(map.get("exp")));
					Long currentDate = System.currentTimeMillis() / 1000;

					if(tokenTime <= currentDate){
						return false;
					}
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
//
////					java.util.Date yourUtcDate = sdf.parse(String.valueOf(map.get("exp")));
//
//					System.out.println(System.currentTimeMillis() / 1000);
//					java.util.Date currentDate = new Date();
//					System.out.println("currentDate=" + currentDate);
//					System.out.println(System.currentTimeMillis());
//					java.util.Date yourUtcDate = sdf.parse(String.valueOf(map.get("exp")));
//
//					System.out.println("yourUtcDate=" + yourUtcDate);
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			return false;
		}
		return true;
	}

	private JsonNode decodeAndParse(String b64String){
		JsonNode jwtHeader = null;

		try {
			 String jsonString = new String(decoder.decode(b64String), "UTF-8");
		     jwtHeader = mapper.readValue(jsonString, JsonNode.class);
		} catch (Exception e) {
			logger.info(e.getMessage());
			jwtHeader = null;
		}

        return jwtHeader;
    }

//	private String getAlgorithm(JsonNode jwtHeader) {
//        final String algorithmName = jwtHeader.has("alg") ? jwtHeader.get("alg").asText() : null;
//
//        if (jwtHeader.get("alg") == null) {
//            throw new IllegalStateException("algorithm not set");
//        }
//
//        if (algorithms.get(algorithmName) == null) {
//            throw new IllegalStateException("unsupported algorithm");
//        }
//
//        return algorithms.get(algorithmName);
//    }
//
//	 void verifySignature(String[] pieces, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
//        Mac hmac = Mac.getInstance(algorithm);
//        hmac.init(new SecretKeySpec(secret, algorithm));
//        byte[] sig = hmac.doFinal(new StringBuilder(pieces[0]).append(".").append(pieces[1]).toString().getBytes());
//
//        if (!MessageDigest.isEqual(sig, decoder.decode(pieces[2]))) {
//            throw new SignatureException("signature verification failed");
//        }
//    }
//
//    void verifyExpiration(JsonNode jwtClaims) throws JWTExpiredException {
//        final long expiration = jwtClaims.has("exp") ? jwtClaims.get("exp").asLong(0) : 0;
//
//        if (expiration != 0 && System.currentTimeMillis() / 1000L >= expiration) {
//            throw new JWTExpiredException("jwt expired", expiration);
//        }
//    }
//
//    void verifyIssuer(JsonNode jwtClaims) throws JWTIssuerException {
//        final String issuerFromToken = jwtClaims.has("iss") ? jwtClaims.get("iss").asText() : null;
//
//        if (issuerFromToken != null && issuer != null && !issuer.equals(issuerFromToken)) {
//            throw new JWTIssuerException("jwt issuer invalid", issuerFromToken);
//        }
//    }
//
//    void verifyAudience(JsonNode jwtClaims) throws JWTAudienceException {
//        if (audience == null)
//            return;
//        JsonNode audNode = jwtClaims.get("aud");
//        if (audNode == null)
//            return;
//        if (audNode.isArray()) {
//            for (JsonNode jsonNode : audNode) {
//                if (audience.equals(jsonNode.textValue()))
//                    return;
//            }
//        } else if (audNode.isTextual()) {
//            if (audience.equals(audNode.textValue()))
//                return;
//        }
//        throw new JWTAudienceException("jwt audience invalid", audNode);
//    }
}
