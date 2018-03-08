package com.fpt.idoctor.common.util;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class PathUtil {
	public static String getAbsoluteResourcePath() throws IOException {
		Resource resource = new ClassPathResource("static");
		return resource.getFile().getAbsolutePath();
	}
}
