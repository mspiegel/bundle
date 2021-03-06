/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.addthis.bundle.value;

import com.addthis.basis.util.Bytes;

import com.google.common.primitives.Longs;

public final class DefaultLong implements ValueLong {

    protected DefaultLong(long value) {
        this.value = value;
    }

    private long value;

    @Override
    public int hashCode() {
        return Longs.hashCode(value);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DefaultLong)) {
            return false;
        }
        return ((DefaultLong) o).value == value;
    }

    @Override
    public ValueNumber avg(int count) {
        return new DefaultLong(value / Math.max(count, 1));
    }

    @Override
    public ValueNumber diff(ValueNumber val) {
        return new DefaultLong(value - val.asLong().getLong());
    }

    @Override
    public ValueNumber max(ValueNumber val) {
        return new DefaultLong((val == null) ? value : Math.max(value, val.asLong().getLong()));
    }

    @Override
    public ValueNumber min(ValueNumber val) {
        return new DefaultLong((val == null) ? value : Math.min(value, val.asLong().getLong()));
    }

    @Override
    public ValueNumber sum(ValueNumber val) {
        return new DefaultLong(value + (val != null ? val.asLong().getLong() : 0));
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    @Override
    public TYPE getObjectType() {
        return TYPE.INT;
    }

    @Override
    public ValueBytes asBytes() throws ValueTranslationException {
        return ValueFactory.create(Bytes.toBytes(value));
    }

    @Override
    public ValueArray asArray() throws ValueTranslationException {
        ValueArray arr = ValueFactory.createArray(1);
        arr.add(this);
        return arr;
    }

    @Override
    public ValueMap asMap() throws ValueTranslationException {
        throw new ValueTranslationException();
    }

    @Override
    public ValueNumber asNumber() throws ValueTranslationException {
        return this;
    }

    @Override
    public ValueLong asLong() throws ValueTranslationException {
        return this;
    }

    @Override
    public ValueDouble asDouble() throws ValueTranslationException {
        return ValueFactory.create((double) value);
    }

    @Override
    public ValueString asString() throws ValueTranslationException {
        return ValueFactory.create(Long.toString(value));
    }

    @Override
    public long getLong() {
        return value;
    }

    @Override
    public ValueCustom asCustom() throws ValueTranslationException {
        throw new ValueTranslationException();
    }
}
