package org.serbuitrago.model;

import java.util.Properties;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

public class EnabledTest {

	@Test
	@Disabled
	void systemProperties() {
		Properties properties = System.getProperties();
		properties.forEach((k, v) -> System.out.println("" + k + ": " + v));
	}

	@Nested
	@Tag("Enable")
	class EnabledSO {
		@Test
		@EnabledOnOs(OS.WINDOWS)
		void soWindows() {
			System.out.println("Test SO Windows.");
		}

		@Test
		@EnabledOnOs({ OS.LINUX, OS.MAC })
		void soLinuxMac() {
			System.out.println("Test SO Linux y Mac.");
		}

		@Test
		@DisabledOnOs(OS.WINDOWS)
		void soNoWindows() {
			System.out.println("Test no SO Windows.");
		}
	}

	@Nested
	@Tag("Enable")
	class EnabledJava {
		@Test
		@EnabledOnJre(JRE.JAVA_8)
		void jdk8() {
			System.out.println("Test solo Java 8.");
		}

		@Test
		@EnabledOnJre(JRE.JAVA_11)
		void jdk11() {
			System.out.println("Test solo Java 11.");
		}

		@Test
		@EnabledOnJre(JRE.JAVA_15)
		void jdk15() {
			System.out.println("Test solo Java 15.");
		}

		@Test
		@DisabledOnJre(JRE.JAVA_8)
		void noJdk8() {
			System.out.println("Test no Java 8.");
		}

		@Test
		@DisabledOnJre(JRE.JAVA_11)
		void noJdk11() {
			System.out.println("Test no Java 11.");
		}

		@Test
		@DisabledOnJre(JRE.JAVA_15)
		void noJdk15() {
			System.out.println("Test no Java 15.");
		}
	}

	@Nested
	@Tag("Enable")
	class EnabledSystemProperty {
		@Test
		@EnabledIfSystemProperty(named = "java.version", matches = "17.0.1")
		void javaVersion() {
			System.out.println("Version java correcta.");
		}

		@Test
		@EnabledIfSystemProperty(named = "java.version", matches = ".*17.*")
		void javaVersionMatches() {
			System.out.println("Version java correcta con expresion regular.");
		}

		@Test
		@DisabledIfSystemProperty(named = "os.arch", matches = ".*32.*")
		void noSystem32() {
			System.out.println("No en sistemas con arquitectura de 32.");
		}

		@Test
		@EnabledIfSystemProperty(named = "os.arch", matches = ".*32.*")
		void noSystem64() {
			System.out.println("Solo en arquitectura de 32.");
		}

		@Test
		@EnabledIfSystemProperty(named = "user.name", matches = "sergio.barrios")
		void username() {
			System.out.println("Solo usuario sergio.barrios.");
		}
	}
}
