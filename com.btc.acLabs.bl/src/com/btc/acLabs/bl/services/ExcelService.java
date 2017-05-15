package com.btc.acLabs.bl.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.btc.acLabs.bl.dmos.Requirement;

public interface ExcelService {

	void exportRequirements(File output, List<Requirement> requirements) throws IOException;
	
	boolean importRequirements(FileInputStream file) throws IOException;
}
