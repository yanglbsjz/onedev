package io.onedev.server.util;

import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class ContentDetectorTest {

	@Test
	public void detectCharset() {
		try (var is = Resources.getResource(ContentDetectorTest.class, "iso-8859-1.txt").openStream()) {
			var bytes = IOUtils.toByteArray(is);
			assertEquals(StandardCharsets.ISO_8859_1, ContentDetector.detectCharset(bytes));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try (var is = Resources.getResource(ContentDetectorTest.class, "utf-8.txt").openStream()) {
			var bytes = IOUtils.toByteArray(is);
			assertEquals(StandardCharsets.UTF_8, ContentDetector.detectCharset(bytes));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try (var is = Resources.getResource(ContentDetectorTest.class, "gb18030.txt").openStream()) {
			var bytes = IOUtils.toByteArray(is);
			assertEquals("GB18030", ContentDetector.detectCharset(bytes).name());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}