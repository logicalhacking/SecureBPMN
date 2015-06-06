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

package eu.aniketos.securebpmn.xacml.pdp.activiti;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import eu.aniketos.securebpmn.xacml.pdp.PDPServer;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.PDPConfig;

public class Integration__PDPRequest {

    @Test
    public void test() throws Exception {
        ConfigurationStore config = new ConfigurationStore(new File("src/test/java/eu.aniketos.securebpmn.xacml/pdp/activiti/policy-config.xml"));
        PDPConfig conf = config.getPDPConfig("pdp");
        PDPServer pdp = new PDPServer(conf);
        File requestFile = new File("src/test/java/eu.aniketos.securebpmn.xacml/pdp/activiti/request.xml");
        String request = readRequestFromFile(requestFile);
        String result = pdp.evaluateXACML(request);
        System.out.println(result);
    }

    private String readRequestFromFile(File requestFile)
    throws FileNotFoundException, IOException {
        final InputStream in = new FileInputStream(requestFile);
        String request = null;
        try {
            StringBuilder builder = new StringBuilder();
            final byte[] buffer = new byte[4096];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                byte[] buffer_ = new byte[len];
                System.arraycopy(buffer, 0, buffer_, 0, len);
                builder.append(new String(buffer_));
            }
            request = builder.toString();
        } finally {
            in.close();
        }
        return request;
    }

}
