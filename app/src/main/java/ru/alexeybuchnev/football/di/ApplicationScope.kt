package ru.alexeybuchnev.football.di

import javax.inject.Scope

/**
 * Этой аннотацией помечается компонент и модули/проввайдеры которые
 * будут жить столько же сколько помеченный компонент
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope
