package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = false)
@Entity(tableName = "family_groups")
data class FamilyGroupEntity(
    @PrimaryKey
    @Json(name = "group_id") val groupId: String = UUID.randomUUID().toString(),
    @Json(name = "family_name") val familyName: String,
    @Json(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)

@JsonClass(generateAdapter = false)
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @Json(name = "user_id") val userId: String = UUID.randomUUID().toString(),
    @Json(name = "group_id") val groupId: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String = "",
    @Json(name = "role") val role: String, // "parent" or "child"
    @Json(name = "name") val name: String,
    @Json(name = "pin") val pin: String = "1234",
    @Json(name = "avatar") val avatar: String = "default"
)

@JsonClass(generateAdapter = false)
@Entity(tableName = "devices")
data class DeviceEntity(
    @PrimaryKey
    @Json(name = "device_id") val deviceId: String = UUID.randomUUID().toString(),
    @Json(name = "user_id") val userId: String,
    @Json(name = "device_name") val deviceName: String,
    @Json(name = "token") val token: String = UUID.randomUUID().toString(),
    @Json(name = "battery_percent") val batteryPercent: Int = 85,
    @Json(name = "is_online") val isOnline: Boolean = true,
    @Json(name = "last_active") val lastActive: Long = System.currentTimeMillis()
)

@JsonClass(generateAdapter = false)
@Entity(tableName = "activity_logs")
data class ActivityLogEntity(
    @PrimaryKey
    @Json(name = "log_id") val logId: String = UUID.randomUUID().toString(),
    @Json(name = "child_id") val childId: String,
    @Json(name = "package_name") val packageName: String,
    @Json(name = "app_name") val appName: String,
    @Json(name = "content_title") val contentTitle: String = "",
    @Json(name = "extracted_text") val extractedText: String = "",
    @Json(name = "is_flagged") val isFlagged: Boolean = false,
    @Json(name = "threat_category") val threatCategory: String? = null,
    @Json(name = "confidence_score") val confidenceScore: Float = 0.0f,
    @Json(name = "ai_analysis_summary") val aiAnalysisSummary: String = "",
    @Json(name = "recorded_at") val recordedAt: Long = System.currentTimeMillis(),
    @Json(name = "is_synced") val isSynced: Boolean = false,
    @Json(name = "is_acknowledged") val isAcknowledged: Boolean = false
)

@JsonClass(generateAdapter = false)
@Entity(tableName = "schedule_rules")
data class ScheduleRuleEntity(
    @PrimaryKey
    @Json(name = "rule_id") val ruleId: String = UUID.randomUUID().toString(),
    @Json(name = "child_id") val childId: String,
    @Json(name = "rule_name") val ruleName: String,
    @Json(name = "category") val category: String, // "Study", "Homework", "Bedtime", "Outdoor", "Free Time"
    @Json(name = "start_time") val startTime: String, // "15:00"
    @Json(name = "end_time") val endTime: String,   // "17:00"
    @Json(name = "day_of_week") val dayOfWeek: String = "Mon,Tue,Wed,Thu,Fri,Sat,Sun",
    @Json(name = "restricted_packages") val restrictedPackages: String, // comma-separated package names
    @Json(name = "is_active") val isActive: Boolean = true
)

@JsonClass(generateAdapter = false)
@Entity(tableName = "location_history")
data class LocationPointEntity(
    @PrimaryKey
    @Json(name = "loc_id") val locId: String = UUID.randomUUID().toString(),
    @Json(name = "child_id") val childId: String,
    @Json(name = "latitude") val latitude: Double,
    @Json(name = "longitude") val longitude: Double,
    @Json(name = "accuracy") val accuracy: Float = 5.0f,
    @Json(name = "location_name") val locationName: String = "Current Location",
    @Json(name = "recorded_at") val recordedAt: Long = System.currentTimeMillis(),
    @Json(name = "is_synced") val isSynced: Boolean = false
)

data class RestrictedAppInfo(
    val packageName: String,
    val appName: String,
    val iconName: String,
    val category: String,
    val isInstalledOnDevice: Boolean = false
)

@JsonClass(generateAdapter = false)
@Entity(tableName = "installed_apps")
data class InstalledAppEntity(
    @PrimaryKey
    @Json(name = "id") val id: String, // childId + "_" + packageName
    @Json(name = "child_id") val childId: String,
    @Json(name = "package_name") val packageName: String,
    @Json(name = "app_name") val appName: String,
    @Json(name = "category") val category: String,
    @Json(name = "is_blocked") val isBlocked: Boolean = false,
    @Json(name = "is_system_app") val isSystemApp: Boolean = false,
    @Json(name = "last_updated") val lastUpdated: Long = System.currentTimeMillis()
)

val PREDEFINED_RESTRICTED_APPS = listOf(
    RestrictedAppInfo("com.google.android.youtube", "YouTube", "video", "Entertainment / Streaming"),
    RestrictedAppInfo("com.zhiliaoapp.musically", "TikTok", "social", "Social Media"),
    RestrictedAppInfo("com.roblox.client", "Roblox", "game", "Gaming"),
    RestrictedAppInfo("com.instagram.android", "Instagram", "social", "Social Media"),
    RestrictedAppInfo("com.discord", "Discord", "chat", "Messaging / Community"),
    RestrictedAppInfo("com.snapchat.android", "Snapchat", "camera", "Ephemeral Social"),
    RestrictedAppInfo("com.netflix.mediaclient", "Netflix", "video", "Entertainment / Video"),
    RestrictedAppInfo("com.epicgames.fortnite", "Fortnite", "game", "Gaming")
)
