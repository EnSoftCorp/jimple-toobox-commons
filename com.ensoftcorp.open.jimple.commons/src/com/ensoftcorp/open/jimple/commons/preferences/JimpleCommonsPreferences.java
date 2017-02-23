package com.ensoftcorp.open.jimple.commons.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.ensoftcorp.open.jimple.commons.Activator;
import com.ensoftcorp.open.jimple.commons.log.Log;

public class JimpleCommonsPreferences extends AbstractPreferenceInitializer {

private static boolean initialized = false;
	
	/**
	 * Enable/disable decompiled loop identification
	 */
	public static final String RECOVER_DECOMPILED_LOOPS = "RECOVER_DECOMPILED_LOOPS";
	public static final Boolean RECOVER_DECOMPILED_LOOPS_DEFAULT = false;
	private static boolean decompiledLoopRecoveryValue = RECOVER_DECOMPILED_LOOPS_DEFAULT;
	
	public static boolean isDecompiledLoopRecoveryEnabled(){
		if(!initialized){
			loadPreferences();
		}
		return decompiledLoopRecoveryValue;
	}
	
	/**
	 * Enable/disable loop boundary condition identification
	 */
	public static final String COMPUTE_LOOP_BOUNDARIES = "COMPUTE_LOOP_BOUNDARIES";
	public static final Boolean COMPUTE_LOOP_BOUNDARIES_DEFAULT = false;
	private static boolean computeLoopBoundariesDefault = COMPUTE_LOOP_BOUNDARIES_DEFAULT;
	
	public static boolean isComputeLoopBoundariesEnabled(){
		if(!initialized){
			loadPreferences();
		}
		return computeLoopBoundariesDefault;
	}
	
	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();
		preferences.setDefault(RECOVER_DECOMPILED_LOOPS, RECOVER_DECOMPILED_LOOPS_DEFAULT);
		preferences.setDefault(COMPUTE_LOOP_BOUNDARIES, COMPUTE_LOOP_BOUNDARIES_DEFAULT);
	}
	
	/**
	 * Loads or refreshes current preference values
	 */
	public static void loadPreferences() {
		try {
			IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();
			decompiledLoopRecoveryValue = preferences.getBoolean(RECOVER_DECOMPILED_LOOPS);
			computeLoopBoundariesDefault = preferences.getBoolean(COMPUTE_LOOP_BOUNDARIES);
		} catch (Exception e){
			Log.warning("Error accessing jimple commons analysis preferences, using defaults...", e);
		}
		initialized = true;
	}
}