package org.sonar.plugins.buildbreaker;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.sonar.api.config.Configuration;

public class TestConfiguration implements Configuration {

  private final Map<String, String[]> store = new HashMap<>();

  public void setProperty(String key, String value) {
    String[] array = value.split(",");
    this.store.put(key, array);
  }

  public void setProperty(String key, Boolean value) {
    String stringValue = value.toString();
    this.store.put(key, new String[] {stringValue});
  }

  public void setProperty(String key, Integer value) {
    String stringValue = value.toString();
    this.store.put(key, new String[] {stringValue});
  }

  @Override
  public Optional<String> get(String key) {
    if (this.store.containsKey(key)) {
      return Optional.of(this.store.get(key)[0]);
    }
    return Optional.empty();
  }

  @Override
  public boolean hasKey(String key) {
    return this.store.containsKey(key);
  }

  @Override
  public String[] getStringArray(String key) {
    if (this.store.containsKey(key)) {
      return this.store.get(key);
    }
    return new String[0];
  }
}
