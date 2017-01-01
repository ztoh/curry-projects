package com.chnye.common.able;

import java.util.List;

public interface IListenable<L> {
	void addListener( L listener );
	void removeListener( L listener );
	void clearListener();
}
