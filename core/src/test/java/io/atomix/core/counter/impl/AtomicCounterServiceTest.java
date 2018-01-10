/*
 * Copyright 2017-present Open Networking Foundation
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
package io.atomix.core.counter.impl;

import io.atomix.core.counter.impl.AtomicCounterOperations.Set;
import io.atomix.storage.buffer.Buffer;
import io.atomix.storage.buffer.HeapBuffer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Counter service test.
 */
public class AtomicCounterServiceTest {
  @Test
  public void testSnapshot() throws Exception {
    AtomicCounterStateMachine service = new AtomicCounterStateMachine();
    service.set(new Set(1L));

    Buffer buffer = HeapBuffer.allocate();
    service.backup(buffer);

    service = new AtomicCounterStateMachine();
    service.restore(buffer.flip());

    long value = service.get();
    assertEquals(1, value);
  }
}
