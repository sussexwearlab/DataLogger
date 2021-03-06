/*
 * Copyright (c) 2017. Mathias Ciliberto, Francisco Javier Ordoñez Morales,
 * Hristijan Gjoreski, Daniel Roggen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uk.ac.sussex.wear.android.datalogger.data;

import java.util.Iterator;

public class CommandDCE extends CommandBase {

    private boolean mState;
    private String mMasterSessionId;
    private long mNanosOffset;

    public CommandDCE(){
        this(true, "", -1);
    }

    public CommandDCE(boolean state){
        this(state, "", -1);
    }

    public CommandDCE(boolean state, String masterSessionId, long nanosOffset){
        mState = state;
        mMasterSessionId = masterSessionId;
        mNanosOffset = nanosOffset;
    }

    public boolean getState(){
        return mState;
    }

    public String getMasterSessionId(){
        return mMasterSessionId;
    }

    public long getNanosOffset(){
        return mNanosOffset;
    }

    public Iterator<String> setParams(Iterator<String> itr) throws Exception {
        if (!itr.hasNext())
            throw new Exception("Command '" + COMMAND_DATA_COLLECTION_EVENT + "' is malformed or missing parameters");
        mState = Boolean.parseBoolean(itr.next());
        if (!itr.hasNext())
            throw new Exception("Command '" + COMMAND_DATA_COLLECTION_EVENT + "' is malformed or missing parameters");
        mMasterSessionId = itr.next();
        if (!itr.hasNext())
            throw new Exception("Command '" + COMMAND_DATA_COLLECTION_EVENT + "' is malformed or missing parameters");
        mNanosOffset = Long.parseLong(itr.next());
        return itr;
    }

    @Override
    public String getMessage() {
        return this.COMMAND_START
                + COMMAND_DATA_COLLECTION_EVENT + COMMAND_SEPARATOR
                + Boolean.toString(mState) + PARAMETER_SEPARATOR
                + mMasterSessionId + PARAMETER_SEPARATOR
                + Long.toString(mNanosOffset) + PARAMETER_SEPARATOR;
    }

}
