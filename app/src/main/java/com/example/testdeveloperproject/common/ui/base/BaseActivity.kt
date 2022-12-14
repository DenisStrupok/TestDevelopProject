package com.example.testdeveloperproject.common.ui.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes layoutId: Int): AppCompatActivity(layoutId) {
}