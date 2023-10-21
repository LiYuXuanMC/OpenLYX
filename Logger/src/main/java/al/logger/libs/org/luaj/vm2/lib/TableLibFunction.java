package al.logger.libs.org.luaj.vm2.lib;

import al.logger.libs.org.luaj.vm2.LuaValue;

class TableLibFunction extends LibFunction {
	public LuaValue call() {
		return argerror(1, "table expected, got no value");
	}
}
