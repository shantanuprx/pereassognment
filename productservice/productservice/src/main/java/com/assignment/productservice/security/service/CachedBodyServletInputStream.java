package com.assignment.productservice.security.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

public class CachedBodyServletInputStream extends ServletInputStream {

	private InputStream cachedBodyInputStream;

	public CachedBodyServletInputStream(byte[] cachedBody) {
		this.cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
	}

	@Override
	public boolean isFinished() {
		try {
			return cachedBodyInputStream.available() == 0;
		} catch (IOException e) {
			return true;
		}
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setReadListener(ReadListener listener) {
		// Nothing to perform here.
	}

	@Override
	public int read() throws IOException {
		return cachedBodyInputStream.read();
	}
}
