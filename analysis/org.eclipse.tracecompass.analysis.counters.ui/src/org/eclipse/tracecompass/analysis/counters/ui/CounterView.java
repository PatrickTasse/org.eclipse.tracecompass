/*******************************************************************************
 * Copyright (c) 2017 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.tracecompass.analysis.counters.ui;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.tracecompass.internal.analysis.counters.ui.TriStateFilteredCheckboxTree;
import org.eclipse.tracecompass.tmf.core.signal.TmfTraceSelectedSignal;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;
import org.eclipse.tracecompass.tmf.core.trace.TmfTraceManager;
import org.eclipse.tracecompass.tmf.ui.viewers.TmfViewer;
import org.eclipse.tracecompass.tmf.ui.viewers.xycharts.TmfXYChartViewer;
import org.eclipse.tracecompass.tmf.ui.views.TmfChartView;

/**
 * Main implementation for the counters view.
 *
 * <p>
 * The view is composed of two parts:
 * <ol>
 * <li>CounterTreeViewer (left-hand side)</li>
 * <li>CounterChartViewer (right-hand side)</li>
 * </ol>
 * </p>
 *
 * @author Matthew Khouzam
 * @author Mikael Ferland
 */
public class CounterView extends TmfChartView {

    /**
     * Title of the chart viewer
     */
    public static final String VIEW_TITLE = "Counters"; //$NON-NLS-1$

    /**
     * Constructor
     */
    public CounterView() {
        super(VIEW_TITLE);
    }

    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);

        TmfViewer tree = getLeftChildViewer();
        TmfXYChartViewer chart = getChartViewer();
        if (tree instanceof CounterTreeViewer && chart instanceof CounterChartViewer) {
            ((CounterTreeViewer) tree).setTreeListener((CounterChartViewer) chart);
        }
    }

    @Override
    protected TmfXYChartViewer createChartViewer(Composite parent) {
        return new CounterChartViewer(parent);
    }

    @Override
    protected @NonNull TmfViewer createLeftChildViewer(Composite parent) {
        // Create the tree viewer with a filtered checkbox
        int treeStyle = SWT.MULTI | SWT.H_SCROLL | SWT.FULL_SELECTION;
        CounterTreePatternFilter filter = new CounterTreePatternFilter();
        TriStateFilteredCheckboxTree triStateFilteredCheckboxTree = new TriStateFilteredCheckboxTree(parent, treeStyle, filter, true);
        CounterTreeViewer treeViewer = new CounterTreeViewer(parent, triStateFilteredCheckboxTree);

        // Initialize the tree viewer with the currently selected trace
        ITmfTrace trace = TmfTraceManager.getInstance().getActiveTrace();
        if (trace != null) {
            TmfTraceSelectedSignal signal = new TmfTraceSelectedSignal(this, trace);
            treeViewer.traceSelected(signal);
        }

        return treeViewer;
    }

}