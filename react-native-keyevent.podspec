# frozen_string_literal: true

require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name         = 'react-native-touchevent'
  s.version      = package['version']
  s.summary      = 'Capture external touch events'
  s.author       = 'Javier Santana'

  s.homepage     = 'https://github.com/UhSpot/react-native-touchevent'

  s.license      = 'MIT'
  s.ios.deployment_target = '7.0'
  s.tvos.deployment_target = '9.0'

  s.source       = { git: 'https://github.com/UhSpot/react-native-touchevent.git', tag: s.version.to_s }

  s.source_files = 'ios/**/*.{h,m}'
  s.requires_arc = true

  s.dependency 'React'
end
