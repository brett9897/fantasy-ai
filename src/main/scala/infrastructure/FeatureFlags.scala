package io.github.brett9897.fantasyai
package infrastructure

trait FeatureFlags:
  def isAdvancedStatsEnabled: Boolean

class EnvVarFeatureFlags(val enableAdvancedStats: Boolean) extends FeatureFlags:
  def isAdvancedStatsEnabled: Boolean = enableAdvancedStats