/*
 *  $RCSfile$
 *  $Author: shk3 $
 *  $Date: 2008-09-29 15:12:09 +0100 (Mon, 29 Sep 2008) $
 *  $Revision: 12493 $
 *
 *  Copyright (C) 1997-2007  The JChemPaint project
 *
 *  Contact: jchempaint-devel@lists.sf.net
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1
 *  of the License, or (at your option) any later version.
 *  All we ask is that proper credit is given for our work, which includes
 *  - but is not limited to - adding the above copyright notice to the beginning
 *  of your source code files, and to any copyright notice that you may distribute
 *  with programs based on this work.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.jchempaint.action;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.openscience.jchempaint.application.JChemPaint;
import org.openscience.jchempaint.io.JCPFileFilter;
import org.openscience.jchempaint.io.JCPFileView;

/**
 * Shows the open dialog
 *
 * @author        steinbeck
 * @cdk.module    jchempaint
 */
public class OpenAction extends JCPAction {

	private static final long serialVersionUID = 1030940425527065876L;

	private FileFilter currentFilter = null;

	/**
	 *  Opens an empty JChemPaint frame.
	 *
	 * @param  e  Description of the Parameter
	 */
	public void actionPerformed(ActionEvent e) {

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(jcpPanel.getCurrentWorkDirectory());
		JCPFileFilter.addChoosableFileFilters(chooser);
		if (jcpPanel.getCurrentOpenFileFilter() != null) {
			chooser.setFileFilter(jcpPanel.getCurrentOpenFileFilter());
		}
		if (jcpPanel.getLastOpenedFile() != null) {
			chooser.setSelectedFile(jcpPanel.getLastOpenedFile());
		}
		if (currentFilter != null) {
			chooser.setFileFilter(currentFilter);
		}
		chooser.setFileView(new JCPFileView());

		int returnVal = chooser.showOpenDialog(jcpPanel);

		currentFilter = chooser.getFileFilter();

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			jcpPanel.setCurrentWorkDirectory(chooser.getCurrentDirectory());
			jcpPanel.setCurrentOpenFileFilter(chooser.getFileFilter());

			javax.swing.filechooser.FileFilter ff = chooser.getFileFilter();
			if (ff instanceof JCPFileFilter) {
				type = ((JCPFileFilter) ff).getType();
			}
			JChemPaint.showInstance(chooser.getSelectedFile(),type, jcpPanel);
		}
	}
}
