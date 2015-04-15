package org.exammodule.service;

public interface SecurityContextAccessor {

	boolean isCurrentAuthenticationAnonymous();

	String determineDefaultTargetUrl();
}