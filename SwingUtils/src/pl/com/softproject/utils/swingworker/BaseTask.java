/* 
 * Copyright 2012-11-21 the original author or authors.
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
package pl.com.softproject.utils.swingworker;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * Bazowy task, który może blokować okno aplikacji na czas wykonywania
 * 
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public abstract class BaseTask<T , V> extends AbstractTask<T, V> {

    static final Logger logger = Logger.getLogger(BaseTask.class.getCanonicalName());
    WindowBlocker blocker;
    
    @Override
    protected void failed(Throwable cause) {
        super.failed(cause);
        logger.log(Level.SEVERE, cause.getMessage(), cause);
        JOptionPane.showMessageDialog(null, cause.getLocalizedMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        unBlock();
    }

    @Override
    protected void finished() {
        super.finished();
        unBlock();
    }
    
    public void setBlocker(WindowBlocker blocker) {
        this.blocker = blocker;
    }
    
    protected void block() {
        if(blocker!=null) {
            blocker.block();
        }
    }
    
    protected void unBlock() {
        if(blocker!=null) {
            blocker.unBlock();
        }
    }
}