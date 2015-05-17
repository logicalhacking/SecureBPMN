/* Copyright 2012-2015 SAP SE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.aniketos.securebpmn.export.html.export;

import java.io.IOException;
import java.io.Writer;

/**
 * Writes Process information in HTML format using a Writer.
 *
 */
public class HtmlWriter {

    private Writer writer;
    private boolean coloredRowsEnabled = true;
    private boolean colorRow = false;

    HtmlWriter(Writer writer) {
        this.writer = writer;
    }

    /**
     * Returns if the output will contain colored rows.
     *
     * @return The current setting for colored rows.
     */
    public boolean isColoredRowsEnabled() {
        return coloredRowsEnabled;
    }

    /**
     * Sets if the output will contain colored rows.
     *
     * @param coloredRowsEnabled
     *            true if the output should contain colored rows, false if it
     *            shoud not.
     */
    public void setColoredRowsEnabled(boolean coloredRowsEnabled) {
        this.coloredRowsEnabled = coloredRowsEnabled;
    }

    // HTML Element

    /**
     * Writes the start of an HTML file.
     *
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeStartDocument() throws IOException {
        writer.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" "
                     + "\"http://www.w3.org/TR/html4/loose.dtd\">\n");
        writer.write("<html>\n");
    }

    /**
     * Writes the end of an HTML file, i.e., the closing HTML tag.
     *
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeEndDocument() throws IOException {
        writer.write("</html>");
    }

    // HEAD Element

    /**
     * Writes the HEAD tag of the HTML file.
     *
     * @param documentTitle
     *            The title that should be used for the file.
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeHead(String documentTitle) throws IOException {
        writer.write("<head>\n");
        writer.write("<title>" + documentTitle + "</title>\n");
        writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n");
        writer.write("<meta name=\"generator\" content=\"Activiti Designer\" />\n");
        writer.write("</head>\n");
    }

    // BODY Element

    /**
     * Writes the opening BODY tag of the HTML file.
     *
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeStartBody() throws IOException {
        writer.write("<body>\n");
    }

    /**
     * Writes the closing BODY tag of the HTML file.
     *
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeEndBody() throws IOException {
        writer.write("</body>\n");
    }

    // TABLE, TR, TD, TH Elements

    /**
     * Writes an opening TABLE tag to the HTML file.
     *
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeStartTable() throws IOException {
        writer.write("<table border=\"1\" cellpadding=\"2\">\n");
    }

    /**
     * Writes an closing TABLE tag to the HTML file.
     *
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeEndTable() throws IOException {
        writer.write("</table>\n");
    }

    /**
     * Writes an opening TR tag to the HTML file.
     *
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeStartTableRow() throws IOException {
        writer.write("\t<tr>\n");
    }

    /**
     * Writes an closing TR tag to the HTML file.
     *
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeEndTableRow() throws IOException {
        writer.write("\t</tr>\n");
        if (coloredRowsEnabled)
            colorRow = !colorRow;
    }

    /**
     * Writes a table cell, i.e., the TD tag to the HTML file.
     *
     * @param content
     *            The content that is written in the table cell.
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeTableCell(String content) throws IOException {
        if (coloredRowsEnabled && colorRow) {
            writer.write("\t\t<td bgcolor=\"#F0F0F0\">" + content + "</td>\n");
        } else {
            writer.write("\t\t<td>" + content + "</td>\n");
        }
    }

    /**
     * Writes a table heading cell, i.e., the TH tag to the HTML file.
     *
     * @param content
     *            The content that is written in the heading cell.
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeTableHeadingCell(String content) throws IOException {
        writer.write("\t\t<th>" + content + "</th>\n");
    }

    // H1, H2, H3 Elements

    /**
     * Writes a text heading, i.e., the H1, H2 or H3 tag to the HTML file.
     *
     * @param content
     *            The content that is written as the heading.
     * @param headingNr
     *            1, 2 or 3, depending on the level of the heading you want.
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeHeading(String content, int headingNr) throws IOException {
        if (headingNr < 1 || headingNr > 3)
            return;
        writer.write("<h" + headingNr + ">" + content + "</h" + headingNr
                     + ">\n");
    }

    // HR Element

    /**
     * Writes a horizontal rule, i.e., the HR tag to the HTML file.
     *
     * @throws IOException
     *             IOException that might occur using the writer.
     */
    public void writeHorizontalRule() throws IOException {
        writer.write("<hr>\n");
    }

}
