package com.revature.configuration;

public class WebConfigTest {

//	@Test
//	public void testAddCorsMappings() {
//
//		
//		
//		WebConfig tester = new WebConfig();
//
//		CorsRegistry testInput = new CorsRegistry();
//		tester.addCorsMappings(testInput);
//
//		try {
//			File file = new File("./src/test/resources/WebConfigTest.yml");
//			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//			WebConfigTestYml testProperties = mapper.readValue(file, WebConfigTestYml.class);
//			
//			
//			Method getConfig = testInput.getClass().getDeclaredMethod("getCorsConfigurations");
//			getConfig.setAccessible(true);
//			@SuppressWarnings("unchecked")
//			Map<String, CorsConfiguration> configs = (Map<String, CorsConfiguration>)getConfig.invoke(testInput);
//			
//			//List<String> list = new ArrayList<String> ();
//			
//			for(CorsConfiguration c : configs.values()) {
//				Assert.assertNotNull("Check allowed origins in web config", c.checkOrigin("http://localhost:3000"));
//			}
//
//		} catch (Exception e) {
//			Assert.fail("testAddCorsMappings produced an error");
//		} finally {
//			Assert.fail("Not fully implemented");
//		}
//	}

}
