/**********************************************************************
 * Copyright (c) 2018 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 **********************************************************************/

package org.eclipse.tracecompass.internal.provisional.tmf.core.model.table;

import java.util.List;
import java.util.Objects;

import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.ImmutableList;

/**
 * Base implementation of {@link ITmfVirtualTableModel}.
 *
 * @author Yonni Chen
 * @param <L>
 *            the virtual table line type
 * @since 4.0
 */
public class TmfVirtualRawTableModel<L extends VirtualTableRawLine> implements ITmfVirtualRawTableModel<L> {

    private final List<L> fLines;
    private final long fLowIndex;
    private final long fSize;

    /**
     * Constructor. The data passed in parameter is deep copied
     *
     * @param data
     *            The data associated with the columns
     * @param index
     *            the rank of the first event
     * @param nbTotalEvents
     *            the total number of entries
     */
    public TmfVirtualRawTableModel(List<L> data, long index, long nbTotalEvents) {
        fLowIndex = index;
        fLines = ImmutableList.copyOf(data);
        fSize = nbTotalEvents;
    }

    @Override
    public List<L> getLines() {
        return fLines;
    }

    @Override
    public long getIndex() {
        return fLowIndex;
    }

    @Override
    public long getSize() {
        return fSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fLowIndex, fSize, fLines);
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
        TmfVirtualRawTableModel<?> other = (TmfVirtualRawTableModel<?>) obj;
        return fLowIndex == other.getIndex() &&
                fSize == other.getSize() &&
                fLines.equals(other.fLines);
    }

    @Override
    public String toString() {
        return "Data: " + fLines + //$NON-NLS-1$
                ", Index: " + fLowIndex + //$NON-NLS-1$
                ", Total nb of events: " + fSize; //$NON-NLS-1$
    }
}
