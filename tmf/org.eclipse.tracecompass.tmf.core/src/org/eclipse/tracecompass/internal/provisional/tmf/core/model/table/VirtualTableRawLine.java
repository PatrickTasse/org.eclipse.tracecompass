/**********************************************************************
 * Copyright (c) 2018, 2021 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 **********************************************************************/

package org.eclipse.tracecompass.internal.provisional.tmf.core.model.table;

import java.util.Objects;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tracecompass.tmf.core.model.ICorePropertyCollection;
import org.json.JSONObject;


/**
 * Basic implementation of {@link IVirtualTableLine}
 *
 * @author Simon Delisle
 * @since 4.0
 */
public class VirtualTableRawLine implements ICorePropertyCollection {

    private JSONObject fRawEvent;
    private long findex;
    private int fActiveProperties = 0;

    /**
     * Constructor.
     *
     * @param index
     *            Index for this line
     *
     * @param jsonObject
     *            Data for this line
     */
    public VirtualTableRawLine(long index, JSONObject jsonObject) {
        findex = index;
        fRawEvent = jsonObject;
    }

    public long getIndex() {
        return findex;
    }

    public JSONObject getCellData() {
        return fRawEvent;
    }



    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        VirtualTableRawLine other = (VirtualTableRawLine) obj;
        return fRawEvent.equals(other.getCellData()) &&
                findex == other.getIndex() && fActiveProperties == other.getActiveProperties();
    }

    @Override
    public int hashCode() {
        return Objects.hash(fRawEvent, findex, fActiveProperties);
    }

    @Override
    public int getActiveProperties() {
        return fActiveProperties;
    }

    @Override
    public void setActiveProperties(int activeProperties) {
        fActiveProperties = activeProperties;
    }
}
