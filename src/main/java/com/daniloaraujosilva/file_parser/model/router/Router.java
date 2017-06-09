package com.daniloaraujosilva.file_parser.model.router;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * Class that knows all routes (paths) to the application.
 */
public class Router {

	/**
	 * 
	 */
	private HashMap<String, Object> map;

    /**
     * 
     */
    private Router() {
    	try {
    		initialize();
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    }

    /**
     * 
     * @return
     */
    public static Router getInstance() {
        return Singleton.INSTANCE;
    }

    /**
     * 
     * @return
     */
    public HashMap<String, Object> get() {
    	return map;
    }

    /**
     * 
     * @param key
     * @return
     */
    public File get(String key) {
    	return (File) map.get(key);
    }

    /**
     * 
     * @param basePath
     * @param relativePath
     * @return
     */
    public File get(String basePath, String relativePath) {
    	return new File(get(basePath), relativePath);
    }

    /**
     *
		 * TODO Some application servers need some special treatments when the application is deployed.
		 *
     * @return
     */
    public Boolean isDeployed() {
			map.put("isDeployed", false);

    	return (Boolean) map.get("isDeployed");
    }

    /**
     * 
     */
    private static class Singleton {

        /**
         * 
         */
        private static final Router INSTANCE = new Router();
    }

    /**
		 *
     * TODO Some application servers need some special treatments when the application is deployed.
		 *
     * @throws UnsupportedEncodingException
     */
    private void initialize() throws UnsupportedEncodingException {
    	map = new HashMap<String, Object>();

    	File root = null;
    	if (isDeployed()) {
    	} else {
    		URL currentUrl = Router.class.getResource("/");
    		String temp = URLDecoder.decode(currentUrl.getFile(), "UTF-8");
    		root = new File(temp).getParentFile().getParentFile();

				map.put("/", root);
				map.put("/src", null); // Doesn't exists in this context.
				map.put("/src/main", new File(get("/"), "classes"));
				map.put("/src/main/java", new File(get("/"), "classes/main"));
				map.put("/src/main/resources", new File(get("/"), "resources/main"));
				map.put("/src/test/java", new File(get("/"), "classes/test"));
				map.put("/src/test/resources", new File(get("/"), "resources/test"));
    	}
    }
}